package com.au.example.dgsexample.dgs;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;

@DgsComponent
public class DgsShows {


    @DgsQuery
    public List<Show> dgsShows(@InputArgument Integer score) {
        var shows = List.of(new Show("Stranger Things", 5), new Show("Ozark", 4));

        if (score == null) {
            return shows;
        }else if(score < 1){
            throw new IllegalArgumentException("Score must be greater than 0");

        } else {
            return shows.stream().filter(s -> s.score() == score).toList();
        }
    }

    @DgsQuery
    public List<Show> allDgsShows() {
        var shows = List.of(new Show("Stranger Things", 5), new Show("Ozark", 4));

        return shows.stream().toList();
    }

}
