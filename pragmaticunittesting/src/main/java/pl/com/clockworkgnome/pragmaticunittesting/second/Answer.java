package pl.com.clockworkgnome.pragmaticunittesting.second;

public class Answer {

    private final Question question;
    private final Bool answer;

    public Answer(Question question, Bool answer) {
        this.question = question;
        this.answer = answer;

    }

    public String getQuestionText() {
        return question.getText();
    }

    public boolean match(Answer answer) {
        return this.answer.equals(answer.answer);
    }
}
