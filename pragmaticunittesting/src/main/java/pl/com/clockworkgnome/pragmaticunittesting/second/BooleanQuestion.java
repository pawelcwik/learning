package pl.com.clockworkgnome.pragmaticunittesting.second;

public class BooleanQuestion extends Question {

    private String text;
    private Bool expectedAnswer;

    public BooleanQuestion(Bool answer, String text) {
        this.text = text;
        this.expectedAnswer = answer;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Bool getExpectedAnswer() {
        return expectedAnswer;
    }
}
