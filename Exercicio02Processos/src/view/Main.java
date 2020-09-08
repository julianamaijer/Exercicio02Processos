package view;

import javax.swing.*;

import controller.ProcessosController;

public class Main {

    public static void main(String[] args) {

        ProcessosController processosController = new ProcessosController();

        String os=null;
        String process=null;
        int opcao = 0;

        while (opcao != 99) {
            opcao = Integer.parseInt(JOptionPane.showInputDialog("Menu: \n" +
                    "1 - Identificar o SO\n" +
                    "2 - Listar tarefas\n" +
                    "3 - Matar o processo pelo PID ou nome\n"+
                    "99 - Sair"));

            switch (opcao) {
                case 1:
                    os = processosController.os(os);
                    System.out.println(os);
                    break;
                case 2:
                    os = processosController.os(os);
                    if (os.contains("Windows")){
                        process = "TASKLIST /FO TABLE";
                        processosController.readProcess(process);
                    }else{
                        if (os.contains("Linux")){
                            process = "ps";
                            processosController.readProcess(process);
                        }else{
                            JOptionPane.showMessageDialog(null,"Opção inválida!");
                        }
                    }
                    break;
                case 3:
                    os = processosController.os(os);
                    if (os.contains("Windows")){
                        String param = "2000";
                        processosController.killProcessWindows(param);
                    }else{
                        if (os.contains("Linux")){
                            String param = "1676";
                            processosController.killProcessLinux(param);
                        }else{
                            JOptionPane.showMessageDialog(null,"Opção inválida!");
                        }
                    }
                    break;
                case 99:
                    JOptionPane.showMessageDialog(null,"Você saiu do cadastro!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        }
    }
}