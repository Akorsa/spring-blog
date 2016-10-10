package ru.akorsa.springdata.mvc.controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import ru.akorsa.springdata.jpa.exceptions.UnknownResourceException;
import ru.akorsa.springdata.mvc.AbstractContext;

import static org.hamcrest.object.IsCompatibleType.typeCompatibleWith;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class GeneralControllerTests extends AbstractContext {

    @Autowired
    GeneralController mockController;

    private MockMvc mockMvc;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {

        final ExceptionHandlerExceptionResolver exceptionResolver = new ExceptionHandlerExceptionResolver();
        final StaticApplicationContext applicationContext = new StaticApplicationContext();
        applicationContext.registerBeanDefinition("globalController",
                new RootBeanDefinition(GlobalController.class, null, null));
        exceptionResolver.setApplicationContext(applicationContext);
        exceptionResolver.afterPropertiesSet();

        mockMvc = MockMvcBuilders.standaloneSetup(mockController)
                .setHandlerExceptionResolvers(exceptionResolver).build();

    }

    @Test
    public void homePageTest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(model().hasNoErrors())
                .andExpect(view().name(GeneralController.HOME_VIEW));
    }

    @Test
    public void resourceNotFoundExceptionTest() throws Exception {

        MvcResult result;
        result = mockMvc.perform(get("/badurl"))
                .andExpect(status().isOk())
                .andExpect(view().name(GlobalController.ERROR_404_VIEW))
                .andReturn();

        assertThat(result.getResolvedException().getClass(),
                typeCompatibleWith(UnknownResourceException.class));
    }

}
