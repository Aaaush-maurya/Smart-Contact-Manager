package com.scm.SCM20.helper;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Message {
    private String content;
    @Builder.Default
    private MessageType type= MessageType.green;
}
