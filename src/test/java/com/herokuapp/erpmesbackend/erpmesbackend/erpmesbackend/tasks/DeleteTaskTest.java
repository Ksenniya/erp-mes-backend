package com.herokuapp.erpmesbackend.erpmesbackend.erpmesbackend.tasks;

import com.herokuapp.erpmesbackend.erpmesbackend.erpmesbackend.FillBaseTemplate;
import com.herokuapp.erpmesbackend.erpmesbackend.tasks.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeleteTaskTest extends FillBaseTemplate {

    private List<Task> tasks;
    private Task deletedTask;

    @Before
    public void init() {
        setupToken();
        addOneAdminRequest(true);
        tasks = addTaskRequests(true);
        deletedTask = restTemplate.exchange("/tasks/{id}", HttpMethod.GET,
                new HttpEntity<>(null, requestHeaders), Task.class, 1).getBody();
    }

    @Test
    public void checkIfResponseDoesNotContainDeletedTask() {
        restTemplate.exchange("/tasks/{id}", HttpMethod.DELETE, new HttpEntity<>(null, requestHeaders),
                HttpStatus.class, 1);
        List<Task> fetchedTasks = Arrays.asList(restTemplate.exchange("/tasks", HttpMethod.GET,
                new HttpEntity<>(null, requestHeaders), Task[].class).getBody());
        assertFalse(fetchedTasks.stream().anyMatch(task -> task.checkIfDataEquals(deletedTask)));
    }
}
