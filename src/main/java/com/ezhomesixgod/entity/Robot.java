package com.ezhomesixgod.entity;

/**
 * @author
 * @description
 * @createTime 2018-09-27 10:19
 */
public class Robot {
    private Hand hand;

    private Mouth mouth;

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Mouth getMouth() {
        return mouth;
    }

    public void setMouth(Mouth mouth) {
        this.mouth = mouth;
    }

    public void say(){
        hand.sayHand();

        mouth.sayMouth();
    }
}
