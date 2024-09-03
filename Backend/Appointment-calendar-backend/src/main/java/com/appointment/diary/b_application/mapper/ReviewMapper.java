package com.appointment.diary.b_application.mapper;

import com.appointment.diary.b_application.dto.ReviewDTO;
import com.appointment.diary.b_application.port.out.repository.ServiceRepository;
import com.appointment.diary.b_application.port.out.repository.UserRepository;
import com.appointment.diary.c_domain.model.BO.ReviewBO;
import com.appointment.diary.c_domain.model.Review;
import com.appointment.diary.c_domain.model.Service;
import com.appointment.diary.c_domain.model.UserEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;


    public ReviewMapper (UserRepository userRepository, ServiceRepository serviceRepository, @Lazy ServiceMapper serviceMapper,@Lazy UserMapper userMapper){
        this.userRepository = userRepository;
        this.serviceRepository = serviceRepository;
        this.serviceMapper=serviceMapper;
        this.userMapper=userMapper;
    }

    public ReviewDTO toDTO(Review review) {
        return ReviewDTO.builder()
                .id(review.getId())
                .userId(review.getUser().getId())
                .serviceId(review.getService().getId())
                .comment(review.getComment())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public ReviewBO toBO(Review review) {
        return ReviewBO.builder()
                .id(review.getId())
                .user(userMapper.toBO(review.getUser()))
                .service(serviceMapper.toBO(review.getService()))
                .comment(review.getComment())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public Review toEntity(ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setId(reviewDTO.getId());
        review.setComment(reviewDTO.getComment());
        review.setRating(reviewDTO.getRating());

        UserEntity user = userRepository.findById(reviewDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Service service = serviceRepository.findById(reviewDTO.getServiceId())
                .orElseThrow(() -> new IllegalArgumentException("Service not found"));

        review.setUser(user);
        review.setService(service);

        return review;
    }

    public Review toEntity(ReviewBO review) {
        return Review.builder()
                .id(review.getId())
                .user(userMapper.toEntity(review.getUser()))
                .service(serviceMapper.toEntity(review.getService()))
                .comment(review.getComment())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
