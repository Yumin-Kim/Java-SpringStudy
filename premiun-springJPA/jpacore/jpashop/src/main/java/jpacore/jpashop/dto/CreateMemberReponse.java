package jpacore.jpashop.dto;


import lombok.Data;


@Data
public class CreateMemberReponse {
    private Long id;

    public CreateMemberReponse(Long joinId) {
        this.id = joinId;
    }

}
