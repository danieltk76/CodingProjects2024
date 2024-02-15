package apps;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Calculator 
{
    private static final int X_LOC = 100;
    private static final int Y_LOC = 100;
    private static final int HEIGHT = 400;
    private static final int WIDTH = 400;
    private static final String NAME = "Calculator ";
    private static final String RESULT_PREAMBLE = "Result = ";
    private static final String ERROR_MESSAGE = "Error ";
    private JFrame frame;
    private JTextField leftOpField;
    private JTextField rightOpField;
    private JLabel resultLabel;



    public Calculator()
    {
        createFrame();
        initializeComponents();
        displayFrame();

    }

    public JFrame getFrame()
    {
        return this.frame;
    }

    private void createFrame()
    {
        frame = new JFrame(NAME);
        frame.setLocation(X_LOC, Y_LOC);
        frame.setSize(HEIGHT, WIDTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    

    }

    private void initializeComponents()
    {
        initializeInputs();
        initializeResults();
        initializeButtons();

    }

    private void displayFrame()
    {
        frame.pack();
        frame.setVisible(true);

    }

    private void initializeInputs()
    {
        JPanel userIn = new JPanel(new FlowLayout(FlowLayout.CENTER));

        
        
        
        leftOpField = new JTextField(10);
        leftOpField.setName("leftOperand");
        rightOpField = new JTextField(10);
        rightOpField.setName("rightOperand");

        userIn.add(leftOpField);
        userIn.add(rightOpField);

        frame.add(userIn, BorderLayout.NORTH);

    }

    private void initializeResults()
    {
        JPanel userRes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        resultLabel = new JLabel(RESULT_PREAMBLE);

        resultLabel.setName("resultLabel");
        userRes.add(resultLabel);
        frame.add(userRes, BorderLayout.CENTER);

        
    }

    private void initializeButtons()
    {
        JPanel userButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addButton = new JButton("ADD");
        addButton.setName("addButton");
        JButton subButton = new JButton("SUB");
        subButton.setName("subButton");
        JButton multButton = new JButton("MULT");
        multButton.setName("multButton");
        JButton divButton = new JButton("DIV");
        divButton.setName("divButton");
        

        addButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            try{
                double leftOperand = Double.parseDouble(leftOpField.getText());
                double rightOperand = Double.parseDouble(rightOpField.getText());

                double result = leftOperand + rightOperand;


                updateResult(result);

            } catch (NumberFormatException | NullPointerException j)
            {
                updateResult(Double.NaN);
            }

            }
        });

        subButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            try {
                double leftOperand = Double.parseDouble(leftOpField.getText());
                double rightOperand = Double.parseDouble(rightOpField.getText());

                double result = leftOperand - rightOperand;
                updateResult(result);
            } catch (NumberFormatException | NullPointerException j)
            {
                updateResult(Double.NaN);
            }
                

            }
        });

        divButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try{
                double leftOperand = Double.parseDouble(leftOpField.getText());
                double rightOperand = Double.parseDouble(rightOpField.getText());

                double result = leftOperand / rightOperand;
                updateResult(result);
                if (rightOperand == 0)
                {
                    updateResult(Double.NaN);
                }
                else 
                {
                    double result2 = leftOperand / rightOperand;
                    updateResult(result2);
                }
            } catch (NumberFormatException | NullPointerException j)
            {
                updateResult(Double.NaN);
            }

            }
        });

        multButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try{
                double leftOperand = Double.parseDouble(leftOpField.getText());
                double rightOperand = Double.parseDouble(rightOpField.getText());

                double result = leftOperand * rightOperand;
                updateResult(result);
                } catch (NumberFormatException | NullPointerException j)
                {
                    updateResult(Double.NaN);
                }

            }
        
            


        });

        userButton.add(addButton);
        userButton.add(subButton);
        userButton.add(multButton);
        userButton.add(divButton);

        frame.add(userButton, BorderLayout.SOUTH);
        


    }

    private double getLeftNum()
    {
        try {
            String text = leftOpField.getText();
 
            if (text == null || text.isEmpty())
            {
             return Double.NaN;
            }
            return Double.parseDouble(text);
         } catch (NumberFormatException | NullPointerException e)
         {
             return Double.NaN;
         } 

    }

    private double getRightNum()
    {
        try{
            String text = rightOpField.getText();

            if (text == null || text.isEmpty())
            {
                return Double.NaN;

            }
            return Double.parseDouble(text);
        } catch (NumberFormatException | NullPointerException e)
        {
            return Double.NaN;
        }
    }

    private void updateResult(double result)
    {
        if (Double.isNaN(result))
        {
            resultLabel.setText(RESULT_PREAMBLE + ERROR_MESSAGE);
        }
        else 
        {
            resultLabel.setText(RESULT_PREAMBLE + result);
        }
        
    
        
    }
  

    
    
}