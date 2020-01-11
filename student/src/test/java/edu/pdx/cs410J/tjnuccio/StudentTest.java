package edu.pdx.cs410J.tjnuccio;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Unit tests for the Student class.  In addition to the JUnit annotations,
 * they also make use of the <a href="http://hamcrest.org/JavaHamcrest/">hamcrest</a>
 * matchers for more readable assertion statements.
 */
public class StudentTest
{

  @Test
  public void studentNamedPatIsNamedPat() {
    String name = "Pat";
    var pat = new Student(name, new ArrayList<>(), 0.0, "Doesn't matter");
    assertThat(pat.getName(), equalTo(name));
  }

  @Test
  public void checkGPA () {
    double gpa = 3.64;
    var s = new Student("Name", new ArrayList<>(), gpa, "Other");
    assertThat(s.getGpa(), equalTo(gpa));
  }

  public Student getName() {
    ArrayList<String> classes = new ArrayList<>();
    classes.add("Algorithms");
    classes.add("Java");
    classes.add("Operating Systems");
    return new Student("Tj", classes, 3.64, "Male");
  }

  @Test
  public void toStringContainsName() {
    assertThat(getName().toString(), containsString("Tj"));
  }

  @Test
  public void toStringContainsGpa() {
    assertThat(getName().toString(), containsString("has a gpa of 3.64"));
  }

}
