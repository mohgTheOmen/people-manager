//package org.example.peoplemanager.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Person {
//
//    private Long id;
//
//    private String firstName;
//    private String lastName;
//    private String email;
//    private String group;
//
//    private List<String> skills;
//}

package org.example.peoplemanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "people")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    private String groupName;

    @ElementCollection
    @CollectionTable(
            name = "person_skills",
            joinColumns = @JoinColumn(name = "person_id")
    )
    @Column(name = "skill")
    private List<String> skills = new ArrayList<>();
}