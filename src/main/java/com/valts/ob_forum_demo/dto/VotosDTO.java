package com.valts.ob_forum_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VotosDTO {
    private Integer positiveVotes;
    private Integer negativeVotes;
}
