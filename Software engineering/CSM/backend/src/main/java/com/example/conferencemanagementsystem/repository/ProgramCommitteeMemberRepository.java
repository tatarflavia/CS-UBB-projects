package com.example.conferencemanagementsystem.repository;

import com.example.conferencemanagementsystem.model.ProgramCommitteeMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProgramCommitteeMemberRepository extends JpaRepository<ProgramCommitteeMember, Integer> {

    @Query(value = "select * from program_committee_member where user_username = :username and id in (select program_committee_id from conference_program_committee where conference_id = :id_conference)", nativeQuery = true)
    Optional<ProgramCommitteeMember> findByUsername(@Param("username") String username, @Param("id_conference") int id_conference);
}
