/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameemulatorpackage;

/**
 *
 * @author rhs-9
 */
public class PlayerConnect4 extends Player {

        private int loss, draw;

        public PlayerConnect4(String name) {
            super(name);
            loss = 0;
            draw = 0;
        }

        public int getLoss() {
            return loss;
        }

        public void setLoss(int loss) {
            this.loss = loss;
        }

        public int getDraw() {
            return draw;
        }

        public void setDraw(int draw) {
            this.draw = draw;
        }
    }
