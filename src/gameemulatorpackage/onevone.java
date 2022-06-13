package gameemulatorpackage;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class onevone extends javax.swing.JFrame {

    String playername1, playername2;
    private int turn = (1 + (int) Math.random() * 2);
    int score, count = 1;
    highscore hh = new highscore();

    public onevone() {
        JFrame f = new JFrame("Enter your name");

        do {
            playername1 = JOptionPane.showInputDialog(f, "Enter Player 1 Name:");

        } while (playername1 == null || playername1.equals(""));

        do {
            playername2 = JOptionPane.showInputDialog(f, "Enter Player 2 Name:");
        } while (playername2 == null || playername2.equals(""));

        System.out.println(playername1 + "\n" + playername2);

        initComponents();
        if (turn == 1) {
            turnmessage.setText(playername1);
        } else {
            turnmessage.setText(playername2);
        }

        play1label.setText(playername1 + " is ");
        play2label.setText(playername2 + " is ");
    }

    public void wincon1() {
        if (one.getText().equals("X") && two.getText().equals("X") && three.getText().equals("X")) {
            turnmessage.setText(playername1 + " Won");
            JOptionPane.showMessageDialog(null, playername1 + " Won");
            hh.players.add(new highscore(playername1, 1, 0, 0));
            hh.scoreupdator();
            hh.players.add(new highscore(playername2, 0, 0, 1));
            hh.scoreupdator();
            restart();
            this.setVisible(false);
        } else if (one.getText().equals("X") && four.getText().equals("X") && seven.getText().equals("X")) {
            turnmessage.setText(playername1 + " Won");
            JOptionPane.showMessageDialog(null, playername1 + " Won");
            hh.players.add(new highscore(playername1, 1, 0, 0));
            hh.scoreupdator();
            hh.players.add(new highscore(playername2, 0, 0, 1));
            hh.scoreupdator();
            restart();
            this.setVisible(false);
        } else if (one.getText().equals("X") && five.getText().equals("X") && nine.getText().equals("X")) {
            turnmessage.setText(playername1 + " Won");
            JOptionPane.showMessageDialog(null, playername1 + " Won");
            hh.players.add(new highscore(playername1, 1, 0, 0));
            hh.scoreupdator();
            hh.players.add(new highscore(playername2, 0, 0, 1));
            hh.scoreupdator();
            restart();
            this.setVisible(false);
        } else if (two.getText().equals("X") && five.getText().equals("X") && eight.getText().equals("X")) {
            turnmessage.setText(playername1 + " Won");
            JOptionPane.showMessageDialog(null, playername1 + " Won");
            hh.players.add(new highscore(playername1, 1, 0, 0));
            hh.scoreupdator();
            hh.players.add(new highscore(playername2, 0, 0, 1));
            hh.scoreupdator();
            restart();
            this.setVisible(false);
        } else if (three.getText().equals("X") && six.getText().equals("X") && nine.getText().equals("X")) {
            turnmessage.setText(playername1 + " Won");
            JOptionPane.showMessageDialog(null, playername1 + " Won");
            hh.players.add(new highscore(playername1, 1, 0, 0));
            hh.scoreupdator();
            hh.players.add(new highscore(playername2, 0, 0, 1));
            hh.scoreupdator();
            restart();
            this.setVisible(false);
        } else if (three.getText().equals("X") && five.getText().equals("X") && seven.getText().equals("X")) {
            turnmessage.setText(playername1 + " Won");
            JOptionPane.showMessageDialog(null, playername1 + " Won");
            hh.players.add(new highscore(playername1, 1, 0, 0));
            hh.scoreupdator();
            hh.players.add(new highscore(playername2, 0, 0, 1));
            hh.scoreupdator();
            restart();
            this.setVisible(false);
        } else if (four.getText().equals("X") && five.getText().equals("X") && six.getText().equals("X")) {
            turnmessage.setText(playername1 + " Won");
            JOptionPane.showMessageDialog(null, playername1 + " Won");
            hh.players.add(new highscore(playername1, 1, 0, 0));
            hh.scoreupdator();
            hh.players.add(new highscore(playername2, 0, 0, 1));
            hh.scoreupdator();
            restart();
            this.setVisible(false);
        } else if (seven.getText().equals("X") && eight.getText().equals("X") && nine.getText().equals("X")) {
            turnmessage.setText(playername1 + " Won");
            JOptionPane.showMessageDialog(null, playername1 + " Won");
            hh.players.add(new highscore(playername1, 1, 0, 0));
            hh.scoreupdator();
            hh.players.add(new highscore(playername2, 0, 0, 1));
            hh.scoreupdator();
            restart();
            this.setVisible(false);
        }
    }///////////////////////////////////////////////////////////////////////////////////////////////////////////    

    public void wincon2() {
        if (one.getText().equals("O") && two.getText().equals("O") && three.getText().equals("O")) {
            turnmessage.setText(playername1 + " Won");
            JOptionPane.showMessageDialog(null, playername2 + " Won");
            hh.players.add(new highscore(playername2, 1, 0, 0));
            hh.scoreupdator();
            hh.players.add(new highscore(playername1, 0, 0, 1));
            hh.scoreupdator();
            restart();

            this.setVisible(false);
        } else if (one.getText().equals("O") && four.getText().equals("O") && seven.getText().equals("O")) {
            turnmessage.setText(playername1 + " Won");
            JOptionPane.showMessageDialog(null, playername2 + " Won");
            hh.players.add(new highscore(playername2, 1, 0, 0));
            hh.scoreupdator();
            hh.players.add(new highscore(playername1, 0, 0, 1));
            hh.scoreupdator();
            restart();
            this.setVisible(false);

        } else if (one.getText().equals("O") && five.getText().equals("O") && nine.getText().equals("O")) {
            turnmessage.setText(playername1 + " Won");
            JOptionPane.showMessageDialog(null, playername2 + " Won");
            hh.players.add(new highscore(playername2, 1, 0, 0));
            hh.scoreupdator();
            hh.players.add(new highscore(playername1, 0, 0, 1));
            hh.scoreupdator();
            restart();
            this.setVisible(false);
        } else if (two.getText().equals("O") && five.getText().equals("O") && eight.getText().equals("O")) {
            turnmessage.setText(playername1 + " Won");
            JOptionPane.showMessageDialog(null, playername2 + " Won");
            hh.players.add(new highscore(playername2, 1, 0, 0));
            hh.scoreupdator();
            hh.players.add(new highscore(playername1, 0, 0, 1));
            hh.scoreupdator();
            restart();
            this.setVisible(false);
        } else if (three.getText().equals("O") && six.getText().equals("O") && nine.getText().equals("O")) {
            turnmessage.setText(playername1 + " Won");
            JOptionPane.showMessageDialog(null, playername2 + " Won");
            hh.players.add(new highscore(playername2, 1, 0, 0));
            hh.scoreupdator();
            hh.players.add(new highscore(playername1, 0, 0, 1));
            hh.scoreupdator();
            restart();
            this.setVisible(false);
        } else if (three.getText().equals("O") && five.getText().equals("O") && seven.getText().equals("O")) {
            turnmessage.setText(playername1 + " Won");
            JOptionPane.showMessageDialog(null, playername2 + " Won");
            hh.players.add(new highscore(playername2, 1, 0, 0));
            hh.scoreupdator();
            hh.players.add(new highscore(playername1, 0, 0, 1));
            hh.scoreupdator();
            restart();
            this.setVisible(false);
        } else if (four.getText().equals("O") && five.getText().equals("O") && six.getText().equals("O")) {
            turnmessage.setText(playername1 + " Won");
            JOptionPane.showMessageDialog(null, playername2 + " Won");
            hh.players.add(new highscore(playername2, 1, 0, 0));
            hh.scoreupdator();
            hh.players.add(new highscore(playername1, 0, 0, 1));
            hh.scoreupdator();
            restart();
            this.setVisible(false);
        } else if (seven.getText().equals("O") && eight.getText().equals("O") && nine.getText().equals("O")) {
            turnmessage.setText(playername1 + " Won");
            JOptionPane.showMessageDialog(null, playername2 + " Won");
            hh.players.add(new highscore(playername2, 1, 0, 0));
            hh.scoreupdator();
            hh.players.add(new highscore(playername1, 0, 0, 1));
            hh.scoreupdator();
            restart();
            this.setVisible(false);
        }

    }

    public void checkturn(int n, int t) {
        if (t == 1 && n == 1 && ((String) one.getText()).equals("")) {
            one.setText("X");
            turnmessage.setText(playername2);
            System.out.println(playername1);
            wincon1();
            switchturn();
        } else if (t == 1 && n == 2 && ((String) two.getText()).equals("")) {
            two.setText("X");
            turnmessage.setText(playername2);
            wincon1();
            switchturn();
        } else if (t == 1 && n == 3 && ((String) three.getText()).equals("")) {
            three.setText("X");
            turnmessage.setText(playername2);
            wincon1();
            switchturn();
        } else if (t == 1 && n == 4 && ((String) four.getText()).equals("")) {
            four.setText("X");
            turnmessage.setText(playername2);
            wincon1();
            switchturn();
        } else if (t == 1 && n == 5 && ((String) five.getText()).equals("")) {
            five.setText("X");
            turnmessage.setText(playername2);
            wincon1();
            switchturn();
        } else if (t == 1 && n == 6 && ((String) six.getText()).equals("")) {
            six.setText("X");
            turnmessage.setText(playername2);
            wincon1();
            switchturn();
        } else if (t == 1 && n == 7 && ((String) seven.getText()).equals("")) {
            seven.setText("X");
            turnmessage.setText(playername2);
            wincon1();
            switchturn();
        } else if (t == 1 && n == 8 && ((String) eight.getText()).equals("")) {
            eight.setText("X");
            turnmessage.setText(playername2);
            wincon1();
            switchturn();
        } else if (t == 1 && n == 9 && ((String) nine.getText()).equals("")) {
            nine.setText("X");
            turnmessage.setText(playername2);
            wincon1();
            switchturn();
        }
    }

    public void checkturn1(int n, int t) {
        if (t == 2 && n == 1 && ((String) one.getText()).equals("")) {
            one.setText("O");
            turnmessage.setText(playername1);
            System.out.println(playername1);
            one.setForeground(Color.YELLOW);
            wincon2();
            switchturn();
        } else if (t == 2 && n == 2 && ((String) two.getText()).equals("")) {
            two.setText("O");
            turnmessage.setText(playername1);
            two.setForeground(Color.YELLOW);
            wincon2();
            switchturn();
        } else if (t == 2 && n == 3 && ((String) three.getText()).equals("")) {
            three.setText("O");
            turnmessage.setText(playername1);
            three.setForeground(Color.YELLOW);
            wincon2();
            switchturn();
        } else if (t == 2 && n == 4 && ((String) four.getText()).equals("")) {
            four.setText("O");
            turnmessage.setText(playername1);
            four.setForeground(Color.YELLOW);
            wincon2();
            switchturn();
        } else if (t == 2 && n == 5 && ((String) five.getText()).equals("")) {
            five.setText("O");
            turnmessage.setText(playername1);
            five.setForeground(Color.YELLOW);
            wincon2();
            switchturn();
        } else if (t == 2 && n == 6 && ((String) six.getText()).equals("")) {
            six.setText("O");
            turnmessage.setText(playername1);
            six.setForeground(Color.YELLOW);
            wincon2();
            switchturn();
        } else if (t == 2 && n == 7 && ((String) seven.getText()).equals("")) {
            seven.setText("O");
            turnmessage.setText(playername1);
            seven.setForeground(Color.YELLOW);
            wincon2();
            switchturn();
        } else if (t == 2 && n == 8 && ((String) eight.getText()).equals("")) {
            eight.setText("O");
            turnmessage.setText(playername1);
            eight.setForeground(Color.YELLOW);
            wincon2();
            switchturn();
        } else if (t == 2 && n == 9 && ((String) nine.getText()).equals("")) {
            nine.setText("O");
            turnmessage.setText(playername1);
            nine.setForeground(Color.YELLOW);
            wincon2();
            switchturn();
        }
    }

    public boolean drawcon() {
        boolean f = true;
        if (one.getText().equals("O") && two.getText().equals("O") && three.getText().equals("O")) {
            f = false;
        }
        if (one.getText().equals("O") && four.getText().equals("O") && seven.getText().equals("O")) {
            f = false;

        }
        if (one.getText().equals("O") && five.getText().equals("O") && nine.getText().equals("O")) {
            f = false;
        }
        if (two.getText().equals("O") && five.getText().equals("O") && eight.getText().equals("O")) {
            f = false;

        }
        if (three.getText().equals("O") && six.getText().equals("O") && nine.getText().equals("O")) {
            f = false;

        }
        if (three.getText().equals("O") && five.getText().equals("O") && seven.getText().equals("O")) {
            f = false;

        }
        if (four.getText().equals("O") && five.getText().equals("O") && six.getText().equals("O")) {
            f = false;

        }
        if (seven.getText().equals("O") && eight.getText().equals("O") && nine.getText().equals("O")) {
            f = false;
        }
        //////////////////////////////////////////////
        if (one.getText().equals("X") && two.getText().equals("X") && three.getText().equals("X")) {
            f = false;
        }
        if (one.getText().equals("X") && four.getText().equals("X") && seven.getText().equals("X")) {
            f = false;

        }
        if (one.getText().equals("X") && five.getText().equals("X") && nine.getText().equals("X")) {
            f = false;
        }
        if (two.getText().equals("X") && five.getText().equals("X") && eight.getText().equals("X")) {
            f = false;

        }
        if (three.getText().equals("X") && six.getText().equals("X") && nine.getText().equals("X")) {
            f = false;

        }
        if (three.getText().equals("X") && five.getText().equals("X") && seven.getText().equals("X")) {
            f = false;

        }
        if (four.getText().equals("X") && five.getText().equals("X") && six.getText().equals("X")) {
            f = false;

        }
        if (seven.getText().equals("X") && eight.getText().equals("X") && nine.getText().equals("X")) {
            f = false;
        }
        return f;
    }

    public void switchturn() {
        System.out.println(turn);
        if (turn == 1) {
            turn = 2;
            count++;
        } else {
            turn = 1;
            count++;
        }
        if (drawcon() == true && count == 10) {
            JOptionPane.showMessageDialog(null, "Draw ");
            hh.players.add(new highscore(playername1, 0, 1, 0));
            hh.scoreupdator();
            hh.players.add(new highscore(playername2, 0, 1, 0));
            hh.scoreupdator();
            restart();
            this.setVisible(false);
        }

    }

    public void restart() {
        tictactoemenu t1 = new tictactoemenu();
        t1.setTitle("tictactoe");
        t1.setVisible(true);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        logopanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        p = new javax.swing.JPanel();
        one = new javax.swing.JButton();
        two = new javax.swing.JButton();
        three = new javax.swing.JButton();
        four = new javax.swing.JButton();
        five = new javax.swing.JButton();
        six = new javax.swing.JButton();
        seven = new javax.swing.JButton();
        eight = new javax.swing.JButton();
        nine = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        turnmessage = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        play1label = new javax.swing.JLabel();
        play2label = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gameemulatorpackage/2122.png"))); // NOI18N

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gameemulatorpackage/tic tac toe(1).png"))); // NOI18N

        javax.swing.GroupLayout logopanel5Layout = new javax.swing.GroupLayout(logopanel5);
        logopanel5.setLayout(logopanel5Layout);
        logopanel5Layout.setHorizontalGroup(
            logopanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logopanel5Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        logopanel5Layout.setVerticalGroup(
            logopanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logopanel5Layout.createSequentialGroup()
                .addGroup(logopanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(logopanel5Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel11))
                    .addGroup(logopanel5Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logopanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logopanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        p.setBackground(new java.awt.Color(204, 204, 204));

        one.setBackground(new java.awt.Color(0, 51, 153));
        one.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        one.setForeground(new java.awt.Color(204, 0, 51));
        one.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        one.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                oneMouseClicked(evt);
            }
        });
        one.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneActionPerformed(evt);
            }
        });

        two.setBackground(new java.awt.Color(0, 51, 153));
        two.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        two.setForeground(new java.awt.Color(204, 0, 51));
        two.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        two.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                twoMouseClicked(evt);
            }
        });
        two.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twoActionPerformed(evt);
            }
        });

        three.setBackground(new java.awt.Color(0, 51, 153));
        three.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        three.setForeground(new java.awt.Color(204, 0, 51));
        three.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        three.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                threeMouseClicked(evt);
            }
        });
        three.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                threeActionPerformed(evt);
            }
        });

        four.setBackground(new java.awt.Color(0, 51, 153));
        four.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        four.setForeground(new java.awt.Color(204, 0, 51));
        four.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        four.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fourMouseClicked(evt);
            }
        });

        five.setBackground(new java.awt.Color(0, 51, 153));
        five.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        five.setForeground(new java.awt.Color(204, 0, 51));
        five.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        five.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fiveMouseClicked(evt);
            }
        });

        six.setBackground(new java.awt.Color(0, 51, 153));
        six.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        six.setForeground(new java.awt.Color(204, 0, 51));
        six.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        six.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sixMouseClicked(evt);
            }
        });

        seven.setBackground(new java.awt.Color(0, 51, 153));
        seven.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        seven.setForeground(new java.awt.Color(204, 0, 51));
        seven.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        seven.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sevenMouseClicked(evt);
            }
        });

        eight.setBackground(new java.awt.Color(0, 51, 153));
        eight.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        eight.setForeground(new java.awt.Color(204, 0, 51));
        eight.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        eight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eightMouseClicked(evt);
            }
        });

        nine.setBackground(new java.awt.Color(0, 51, 153));
        nine.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        nine.setForeground(new java.awt.Color(204, 0, 51));
        nine.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        nine.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nineMouseClicked(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator10.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator11.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator12.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator12.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator13.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator13.setOrientation(javax.swing.SwingConstants.VERTICAL);

        turnmessage.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        turnmessage.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        turnmessage.setText("Player");
        turnmessage.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Turn:");

        play1label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        play1label.setText("Player1 is ");

        play2label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        play2label.setText("Player2 is ");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("X");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 0));
        jLabel3.setText("O");

        javax.swing.GroupLayout pLayout = new javax.swing.GroupLayout(p);
        p.setLayout(pLayout);
        pLayout.setHorizontalGroup(
            pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(seven, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(eight, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nine, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
            .addGroup(pLayout.createSequentialGroup()
                .addGroup(pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pLayout.createSequentialGroup()
                        .addGroup(pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pLayout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(four, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(five, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(six, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pLayout.createSequentialGroup()
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(one, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(two, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(22, 22, 22)
                                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(three, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pLayout.createSequentialGroup()
                                    .addGroup(pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pLayout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(play1label, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel2)
                                            .addGap(77, 77, 77))
                                        .addGroup(pLayout.createSequentialGroup()
                                            .addGap(152, 152, 152)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(turnmessage, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pLayout.createSequentialGroup()
                                            .addGap(104, 104, 104)
                                            .addComponent(jSeparator6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pLayout.createSequentialGroup()
                                            .addGap(34, 34, 34)
                                            .addComponent(play2label, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(pLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pLayout.createSequentialGroup()
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pLayout.createSequentialGroup()
                    .addContainerGap(235, Short.MAX_VALUE)
                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(236, Short.MAX_VALUE)))
        );
        pLayout.setVerticalGroup(
            pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pLayout.createSequentialGroup()
                .addGroup(pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pLayout.createSequentialGroup()
                        .addGroup(pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(play1label))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(turnmessage))
                        .addGap(18, 18, 18))
                    .addGroup(pLayout.createSequentialGroup()
                        .addGroup(pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(play2label))
                            .addGroup(pLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15)
                        .addComponent(jSeparator6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pLayout.createSequentialGroup()
                        .addComponent(jSeparator5)
                        .addGap(32, 32, 32)))
                .addGroup(pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(one, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                        .addComponent(jSeparator10)
                        .addComponent(two, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(three, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(four, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(six, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(five, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(17, 17, 17)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(seven, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eight, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nine, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator9, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(jSeparator11))
                .addGap(54, 54, 54))
            .addGroup(pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pLayout.createSequentialGroup()
                    .addContainerGap(235, Short.MAX_VALUE)
                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(235, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void threeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_threeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_threeActionPerformed

    private void oneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oneActionPerformed


    }//GEN-LAST:event_oneActionPerformed

    private void oneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_oneMouseClicked
        //zero.setText("X");
        checkturn(1, turn);
        checkturn1(1, turn);
    }//GEN-LAST:event_oneMouseClicked

    private void twoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_twoMouseClicked
        checkturn(2, turn);
        checkturn1(2, turn);
    }//GEN-LAST:event_twoMouseClicked

    private void twoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_twoActionPerformed

    private void threeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_threeMouseClicked
        checkturn(3, turn);
        checkturn1(3, turn);
    }//GEN-LAST:event_threeMouseClicked

    private void fourMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fourMouseClicked
        checkturn(4, turn);
        checkturn1(4, turn);
    }//GEN-LAST:event_fourMouseClicked

    private void fiveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fiveMouseClicked
        checkturn(5, turn);
        checkturn1(5, turn);
    }//GEN-LAST:event_fiveMouseClicked

    private void sixMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sixMouseClicked
        checkturn(6, turn);
        checkturn1(6, turn);
    }//GEN-LAST:event_sixMouseClicked

    private void sevenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sevenMouseClicked
        checkturn(7, turn);
        checkturn1(7, turn);
    }//GEN-LAST:event_sevenMouseClicked

    private void eightMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eightMouseClicked
        checkturn(8, turn);
        checkturn1(8, turn);
    }//GEN-LAST:event_eightMouseClicked

    private void nineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nineMouseClicked
        checkturn(9, turn);
        checkturn1(9, turn);
    }//GEN-LAST:event_nineMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(onevone.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(onevone.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(onevone.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(onevone.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new onevone().setVisible(true);
            }
        });
        JSeparator s = new JSeparator();

        // set layout as vertical 
        s.setOrientation(SwingConstants.VERTICAL);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton eight;
    private javax.swing.JButton five;
    private javax.swing.JButton four;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JPanel logopanel5;
    private javax.swing.JButton nine;
    private javax.swing.JButton one;
    private javax.swing.JPanel p;
    private javax.swing.JLabel play1label;
    private javax.swing.JLabel play2label;
    private javax.swing.JButton seven;
    private javax.swing.JButton six;
    private javax.swing.JButton three;
    private javax.swing.JLabel turnmessage;
    private javax.swing.JButton two;
    // End of variables declaration//GEN-END:variables
}
