package com.example.martin.ciscofullapp.CommandRunner;

import java.io.IOException;
import com.example.martin.ciscofullapp.CommandRunner.FileRunner;
import com.example.martin.ciscofullapp.CommandRunner.CommandRunner;
import com.example.martin.ciscofullapp.CommandRunner.Task;

/**
 * Created by Martin on 13-11-2017.
 */

public class TestFunction {

    //CommandRunner commandRunner = new CommandRunner();
    //Task task = new Task();
    //FileRunner fileRunner = new FileRunner();

    public void run() throws IOException {

        if (!CommandRunner.commandCheck)
        {
            CommandRunner commandRunner = new CommandRunner();
            commandRunner.run();
        }

        if (CommandRunner.commandCheck && !Task.taskCheck)
        {
            Task task = new Task();
            task.run();
        }
        if (CommandRunner.commandCheck && Task.taskCheck)
        {
            FileRunner fileRunner = new FileRunner();
            fileRunner.run();
        }

    }
}
