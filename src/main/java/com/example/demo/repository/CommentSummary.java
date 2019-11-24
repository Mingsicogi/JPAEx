package com.example.demo.repository;

public interface CommentSummary {

    int getUp();

    int getDown();

    boolean isBest();

    default String voteResult(){
        return "" + this.getUp() + " " + this.getDown();
    }
}
