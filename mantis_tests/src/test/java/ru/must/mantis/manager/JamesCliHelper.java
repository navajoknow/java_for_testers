package ru.must.mantis.manager;

import org.openqa.selenium.io.CircularOutputStream;
import org.openqa.selenium.os.CommandLine;

public class JamesCliHelper extends HelperBase {

    public JamesCliHelper(ApplicationManager manager) {
        super(manager);
    }

    public void addUser(String email, String password) {
        CommandLine cmd = new CommandLine(
                "java", "-cp", "\"james-server-jpa-app.lib/*\"",
                "org.apache.james.cli.ServerCmd",
                "AddUser", email, password);
        // устанавливаем рабочую директорию, в которой находятся классы James, чтобы они запустились
        // на текущий момент рабочей директорией является директория проекта
        cmd.setWorkingDirectory(manager.property("james.workingDir"));
        CircularOutputStream out = new CircularOutputStream();
        cmd.copyOutputTo(out);
        cmd.execute();
        cmd.waitFor();
        System.out.println(out);
    }
}
