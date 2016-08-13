package ru.akorsa.springdata.jpa.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import ru.akorsa.springdata.jpa.model.Hobby;

public class HobbyDTO {

    private Long hobbyId;

    @NotEmpty
    @Length(max = Hobby.MAX_LENGTH_HOBBY_TITLE)
    private String hobbyTitle;

    private Boolean deleteFlag = false;

    public HobbyDTO() {
    }

    public HobbyDTO(String hobbyTitle) {
        this.hobbyTitle = hobbyTitle;
    }

    public HobbyDTO(Long hobbyId, String hobbyTitle) {
        this.hobbyId = hobbyId;
        this.hobbyTitle = hobbyTitle;
    }

    public HobbyDTO(Hobby hobby) {
        this.hobbyId = hobby.getHobbyId();
        this.hobbyTitle = hobby.getHobbyTitle();
    }

    public Long getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(Long hobbyId) {
        this.hobbyId = hobbyId;
    }

    public String getHobbyTitle() {
        return hobbyTitle;
    }

    public void setHobbyTitle(String hobbyTitle) {
        this.hobbyTitle = hobbyTitle;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "HobbyDTO{" +
                "hobbyId=" + hobbyId +
                ", hobbyTitle='" + hobbyTitle + '\'' +
                ", deleteFlag=" + deleteFlag +
                '}';
    }
}
