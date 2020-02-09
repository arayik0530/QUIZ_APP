package com.workfront.quiz.dto.answer;

import com.workfront.quiz.entity.AnswerEntity;
import lombok.Data;

@Data
public class CreateAnswerDto {
    private Long id;

    private Long questionId;

    private String text;

    private Boolean isRight;

    public AnswerEntity toEntity(){
        AnswerEntity answer = new AnswerEntity();

        answer.setIsRight(this.isRight);
        answer.setText(this.text);
        return answer;
    }
//
//    public static CreateAnswerDto mapFromEntity(AnswerEntity answer){
//        CreateAnswerDto createAnswerDto = new CreateAnswerDto();
//
//        createAnswerDto.setId(answer.getId());
//        createAnswerDto.setQuestionId(answer.getQuestion().getId());
//        createAnswerDto.setText(answer.getText());
//        createAnswerDto.setIsRight(answer.getIsRight());
//
//        return createAnswerDto;
//    }
}
