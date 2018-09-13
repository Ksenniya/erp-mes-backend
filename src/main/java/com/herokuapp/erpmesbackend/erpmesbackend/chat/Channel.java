package com.herokuapp.erpmesbackend.erpmesbackend.chat;

import com.herokuapp.erpmesbackend.erpmesbackend.employees.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> messages;

    @Column(nullable = false)
    @ManyToMany
    private List<Employee> participants;

    public Channel(String name, List<Employee> participants) {
        this.name = name;
        this.messages = new ArrayList<>();
        this.participants = participants;
    }

    public boolean checkIfDataEquals(Channel channel) {
        return name.equals(channel.getName()) &&
                compareMessages(channel.getMessages()) &&
                compareParticipants(channel.getParticipants());
    }

    private boolean compareMessages(List<Message> messageList) {
        if (messageList.isEmpty())
            return true;
        for (Message message: messages) {
            if (messageList.stream().noneMatch(t -> t.checkIfDataEquals(message)))
                return false;
        }
        return true;
    }

    private boolean compareParticipants(List<Employee> participantList) {
        if (participantList.isEmpty())
            return true;
        for (Employee employee : participants) {
            if (participantList.stream().noneMatch(t -> t.checkIfDataEquals(employee)))
                return false;
        }
        return true;
    }
}