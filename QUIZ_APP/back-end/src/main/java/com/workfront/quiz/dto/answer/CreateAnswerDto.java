package com.workfront.quiz.dto.answer;

import com.workfront.quiz.entity.AnswerEntity;

import java.util.Objects;

public class CreateAnswerDto {
    private Long id;

    private String text;

    private Boolean isRight;

    public AnswerEntity toEntity(){
        AnswerEntity answer = new AnswerEntity();

        answer.setRight(this.isRight);
        answer.setText(this.text);
        return answer;
    }

    public static CreateAnswerDto mapFromEntity(AnswerEntity answer){
        CreateAnswerDto createAnswerDto = new CreateAnswerDto();

        createAnswerDto.setId(answer.getId());
        createAnswerDto.setText(answer.getText());
        createAnswerDto.setRight(answer.getRight());

        return createAnswerDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getRight() {
        return isRight;
    }

    public void setRight(Boolean right) {
        isRight = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateAnswerDto)) return false;
        CreateAnswerDto that = (CreateAnswerDto) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getText(), that.getText()) &&
                Objects.equals(isRight, that.isRight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getText(), isRight);
    }

    @Override
    public String toString() {
        return "CreateAnswerDto{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", isRight=" + isRight +
                '}';
    }
}
