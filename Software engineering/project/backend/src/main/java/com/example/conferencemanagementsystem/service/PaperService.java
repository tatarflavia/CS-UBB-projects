package com.example.conferencemanagementsystem.service;

import com.example.conferencemanagementsystem.model.Paper;
import com.example.conferencemanagementsystem.repository.PaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaperService {
    @Autowired
    PaperRepository paperRepository;

    public void addPaper(Paper paper) {
        paperRepository.save(paper);
    }

    @Transactional
    public void updatePaper(int id, Paper paper) {

        Paper p = paperRepository.getOne(id);
        p.setAbstractPaper(paper.getAbstractPaper());
        p.setFullPaper(paper.getFullPaper());
        p.setPresentation(paper.getPresentation());
    }
}
