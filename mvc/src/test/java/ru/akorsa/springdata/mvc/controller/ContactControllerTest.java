package ru.akorsa.springdata.mvc.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.springframework.web.servlet.view.InternalResourceView;
import ru.akorsa.springdata.jpa.config.ApplicationConfig;
import ru.akorsa.springdata.jpa.dto.ContactDTO;
import ru.akorsa.springdata.jpa.enums.DataConfigProfile;
import ru.akorsa.springdata.jpa.exceptions.ContactNotFoundException;
import ru.akorsa.springdata.jpa.model.Contact;
import ru.akorsa.springdata.jpa.model.ContactTestUtils;
import ru.akorsa.springdata.jpa.service.ContactService;
import ru.akorsa.springdata.mvc.MvcTestUtil;
import ru.akorsa.springdata.mvc.config.WebConfig;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false)
@ContextConfiguration(classes = {WebConfig.class, ApplicationConfig.class})
@Transactional
@ActiveProfiles(DataConfigProfile.H2)
@WebAppConfiguration
public class ContactControllerTest {

    private ContactService mockService;
    private ContactController controller;
    private ContactController h2Controller;
    private MockMvc mockMvc;
    private Contact contact;
    private List<Contact> allContacts;
    private MessageSource mockMessageSource;

    private static final String FIELD_NAME_EMAIL_ADDRESS = "email";
    private static final String FEEDBACK_MESSAGE = "feedbackMessage";

    @Autowired
    private ContactService contactService;

    @Resource
    private Validator validator;

    @Mock
    private SessionStatus mockSessionStatus;

    @Before
    public void setUp() {
        mockSessionStatus = mock(SessionStatus.class);
        mockMessageSource = mock(MessageSource.class);
        mockService = mock(ContactService.class);

        controller = new ContactController(mockService);
        h2Controller = new ContactController(contactService);

        mockMvc = standaloneSetup(controller).build();

        ReflectionTestUtils.setField(controller, "messageSource", mockMessageSource);
        ReflectionTestUtils.setField(controller, "contactService", mockService);

        ReflectionTestUtils.setField(h2Controller, "messageSource", mockMessageSource);
        ReflectionTestUtils.setField(h2Controller, "contactService", contactService);


        // Contact is H2 Contact ID #1 "Summer Glass"
        try {
            contact = contactService.findContactById(1L);
            when(mockService.findContactById(100L)).thenReturn(contact);
        } catch (ContactNotFoundException e) {
            e.printStackTrace();
        }

        allContacts = contactService.findAll();
        when(mockService.findAll()).thenReturn(allContacts);

        //Testing application context
        final ExceptionHandlerExceptionResolver exceptionResolver = new ExceptionHandlerExceptionResolver();
        final StaticApplicationContext applicationContext = new StaticApplicationContext();
        applicationContext.registerBeanDefinition("exceptionController",
                new RootBeanDefinition(ExceptionController.class, null, null));
        exceptionResolver.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        exceptionResolver.setApplicationContext(applicationContext);
        exceptionResolver.afterPropertiesSet();

        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setHandlerExceptionResolvers(exceptionResolver).build();
    }

    @Test
    public void homePageTest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(model().hasNoErrors())
                .andExpect(view().name("home"));
    }

    @Test
    public void getContactByIdJsonTest() throws Exception {

        mockMvc.perform(get("/contact/json/100").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MvcTestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.contactId", is(1)))
                .andExpect(jsonPath("$.lastName", is("Glass")));
    }

    @Test
    public void getContactByIdTest() throws Exception {
        mockMvc.perform(get("/contact/100").contentType(MediaType.TEXT_HTML))
                .andExpect(view().name("view"))
                .andExpect(model().attributeExists("contact"))
                .andExpect(model().attribute("contact", contact));
    }

    @Test
    public void getContactsTest() throws Exception {
        Model model = new BindingAwareModelMap();
        String view = controller.home(model);

        MvcResult result = mockMvc.perform(get("/contacts"))
                .andExpect(view().name("list"))
                .andExpect(model().attributeExists("contacts"))
                .andExpect(model().attribute("contacts",
                        hasItems(allContacts.toArray())))
                .andReturn();

        List<ContactDTO> allContacts = (List<ContactDTO>) result.getModelAndView().getModel().get("contacts");
        assertEquals("there should be 10 contacts", allContacts.size(), 10);

        verify(mockService, times(1)).findAll();
        verifyNoMoreInteractions(mockService);

        assertEquals(ContactController.HOME_VIEW, view);
    }

    @Test
    public void searchContractsTest() throws Exception {

        mockMvc = standaloneSetup(new ContactController(contactService))
                .setSingleView(
                        new InternalResourceView("/WEB-INF/views/list.html"))
                .build();

        // TEST SINGLE CONTACT RETRIEVED

        mockMvc.perform(get("/list").param("lastName", "Glass"))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/contact/1"));

        mockMvc.perform(get("/list").param("lastName", "Bubba"))
                .andExpect(status().isOk())
                .andExpect(model()
                        .attributeHasFieldErrorCode("contact",
                                "lastName",
                                "search.contact.notfound"))
                .andExpect(view().name("search"));


        // TEST EMPTY FORM - ALL CONTACTS RETRIEVED

        mockMvc.perform(get("/list").param("lastName", ""))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("contacts"))
                .andExpect(view().name("redirect:/contacts/"))
                .andExpect(model().attribute("contacts",
                        hasItems(allContacts.toArray())));

    }

    @Test
    public void resourceNotFoundExceptionTest() throws Exception {

        mockMvc.perform(get("/badurl"))
                .andExpect(status().isOk())
                .andExpect(view().name("404"));
    }

    @Test(expected = ContactNotFoundException.class)
    public void contactNotFoundExceptionTest() throws Exception {
        String Id = "13";

        when(contactService.findContactById(Long.valueOf(Id)))
                .thenThrow(new ContactNotFoundException());

        mockMvc.perform(get("/contact/" + Id))
                .andExpect(status().isOk())
                .andExpect(view().name("error"));
    }

    @Test
    public void addContactTest() {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest("POST", "/contact/new");

        Contact contact = ru.akorsa.springdata.jpa.model.ContactTestUtils.newContact();
        BindingResult result = bindAndValidate(mockRequest, contact);

        RedirectAttributes attributes = new RedirectAttributesModelMap();
        initMessageSourceForFeedbackMessage(ContactController.FEEDBACK_MESSAGE_KEY_CONTACT_ADDED);

        String view = h2Controller.addContact(contact, result, mockSessionStatus, attributes);
        String expectedView = createExpectedRedirectViewPath("/contacts/");

        assertEquals(expectedView, view);
        assertEquals(contact.getContactId(), ContactTestUtils.CONTACT_ID);

        assertFeedbackMessage(attributes, ContactController.FEEDBACK_MESSAGE_KEY_CONTACT_ADDED);
    }

    @Test
    public void addContactWithInvalidEmailAddress() {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest("POST", "/contact/new");

        Contact contact = ContactTestUtils.newContact();
        contact.setEmail(ContactTestUtils.INVALID_EMAIL);

        BindingResult result = bindAndValidate(mockRequest, contact);
        RedirectAttributes attributes = new RedirectAttributesModelMap();
        String view = controller.addContact(contact, result, mockSessionStatus, attributes);

        verifyZeroInteractions(mockService);
        assertEquals(ContactController.CONTACT_FORM_VIEW, view);
        assertFieldErrors(result, FIELD_NAME_EMAIL_ADDRESS);
    }

    private BindingResult bindAndValidate(HttpServletRequest request, Object formObject) {
        WebDataBinder binder = new WebDataBinder(formObject);
        binder.setValidator(validator);
        binder.bind(new MutablePropertyValues(request.getParameterMap()));
        binder.getValidator().validate(binder.getTarget(), binder.getBindingResult());
        return binder.getBindingResult();
    }

    private String createExpectedRedirectViewPath(String path) {
        StringBuilder builder = new StringBuilder();
        builder.append("redirect:");
        builder.append(path);
        return builder.toString();
    }

    private void assertFieldErrors(BindingResult result, String... fieldNames) {
        assertEquals(fieldNames.length, result.getFieldErrorCount());
        for (String fieldName : fieldNames) {
            assertNotNull(result.getFieldError());
        }
    }

    private void initMessageSourceForFeedbackMessage(String feedbackMessageCode) {
        when(mockMessageSource.getMessage(eq(feedbackMessageCode), any(Object[].class), any(Locale.class))).thenReturn(FEEDBACK_MESSAGE);
    }

    private void assertFeedbackMessage(RedirectAttributes model, String messageCode) {
        assetFlashMessages(model, messageCode, ContactController.FLASH_MESSAGE_KEY_FEEDBACK);
    }

    private void assetFlashMessages(RedirectAttributes model, String messageCode, String flashMessageParameterName) {
        Map<String, ?> flashMessages = model.getFlashAttributes();
        Object message = flashMessages.get(flashMessageParameterName);
        assertNotNull(message);
        flashMessages.remove(message);
        assertTrue(flashMessages.isEmpty());

        verify(mockMessageSource, times(1)).getMessage(eq(messageCode), any(Object[].class), any(Locale.class));
        verifyNoMoreInteractions(mockMessageSource);
    }
}