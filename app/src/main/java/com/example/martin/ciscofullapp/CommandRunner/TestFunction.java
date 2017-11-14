package com.example.martin.ciscofullapp.CommandRunner;

import java.io.IOException;
import com.example.martin.ciscofullapp.CommandRunner.FileRunner;
import com.example.martin.ciscofullapp.CommandRunner.CommandRunner;
import com.example.martin.ciscofullapp.CommandRunner.Task;

/**
 * Created by Martin on 13-11-2017.
 */

public class TestFunction {

    CommandRunner commandRunner = new CommandRunner();
    Task task = new Task();
    FileRunner fileRunner = new FileRunner();

    public void run() throws IOException {

        if (commandRunner.commandCheck == false)
        {
            commandRunner.run();
        }

        if (commandRunner.commandCheck == true && task.taskCheck == false)
        {
            task.run();
        }
        if (commandRunner.commandCheck == true && task.taskCheck == true)
        {
            fileRunner.run();
        }

    }
}
