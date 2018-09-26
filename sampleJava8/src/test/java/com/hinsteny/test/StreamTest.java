package com.hinsteny.test;

import com.hisoka.pojo.Project;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamTest {

	@Test
	public void firstTest() {
		String[][] projectNamesArray = {{"1", "First Project", "Cisco"}, {"2", "Second Project", "Cisco"}, {"3", "Third Project", "Google"}};

		// A - projects
		List<Project> projects = Stream.of(projectNamesArray).map(data -> new Project(data[0], data[1], data[2])).collect(toList());

		// B - projectsByCisco
		Map<String, List<Project>> projectsByCisco = projects.stream()
				.filter(p -> p.getClient().equals("Cisco"))
				.collect(groupingBy(Project::getClient));

		// C - projectById
		Map<String, Project> projectById = projects.stream().collect(toMap(Project::getId, Function.identity()));

		System.out.println("#A - projects:\n" + projects + "\n");
		System.out.println("#B - Cisco projects:\n" + projectsByCisco + "\n");
		System.out.println("#C - Project by id:\n" + projectById);
		//projectNames.forEach();

	}


}