package uk.co.stevebosman.aoc2023.day19;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkflowTest {
  @ParameterizedTest
  @CsvSource(value = {
          "qn{s<3093:abc,R}| {x=331,m=185,a=1787,s=3092} | qn | R | abc",
          "qn{s<3093:abc,R}| {x=331,m=185,a=1787,s=3093} | qn | R | R",
  }, delimiter = '|')
  void of(final String workflowInput,
          final String partInput,
          final String expectedWorkflowName,
          final String expectedDefaultDestination,
          final String expectedProcessDestination) {
    final var workflow = Workflow.of(workflowInput);
    final var part = Part.of(partInput);
    //
    assertAll(
            ()->assertEquals(expectedWorkflowName, workflow.name()),
            ()->assertEquals(expectedDefaultDestination, workflow.defaultDestination()),
            ()->assertEquals(1, workflow.rules().size())
    );
    final var destination = workflow.process(part);
    assertEquals(expectedProcessDestination, destination);
  }
}
