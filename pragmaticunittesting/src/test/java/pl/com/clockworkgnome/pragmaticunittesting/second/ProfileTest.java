package pl.com.clockworkgnome.pragmaticunittesting.second;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProfileTest {

    Profile profile;
    Question question;
    Answer profileAnswer;

    @Before
    public void create() {
        profile = new Profile("Bull Hockey, inc.");
        question = new BooleanQuestion(Bool.TRUE, "Got bonuses?");
        profileAnswer = new Answer(question, Bool.FALSE);
    }


    @Test
    public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
        profile.add(profileAnswer);
        Criteria criteria = new Criteria();
        Answer criteriaAnswer = new Answer(question, Bool.TRUE);
        Criterion criterion = new Criterion(criteriaAnswer, Weight.MustMatch);
        criteria.add(criterion);

        boolean matches = profile.matches(criteria);
        assertFalse(matches);
    }

    @Test
    public void matchAnswersTrueForAnyDontCareCriteria() {
        profile.add(profileAnswer);
        Criteria criteria = new Criteria();
        Answer criteriaAnswer = new Answer(question, Bool.TRUE);
        Criterion criterion = new Criterion(criteriaAnswer, Weight.DontCare);
        criteria.add(criterion);

        boolean matches = profile.matches(criteria);
        assertTrue(matches);
    }
}
