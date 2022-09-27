package com.testproject.marketplace.dto.product;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class ProductLikeDTO {

    private Set<String> usersLiked;
    private Set<String> usersUnliked;

}
