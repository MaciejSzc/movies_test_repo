package pl.oskarpolak.movies.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.oskarpolak.movies.models.entities.VoteEntity;
import pl.oskarpolak.movies.models.repositories.VoteRepository;

import java.util.Optional;

@Service
public class VoteService {

    @Autowired
    VoteRepository voteRepository;

    @Transactional
    public void incrementDownVote(int moveId){
        voteRepository.incrementDownVote(moveId);
    }

    @Transactional
    public void incrementUpVote(int moveId){
        voteRepository.incrementUpVote(moveId);
    }

}
