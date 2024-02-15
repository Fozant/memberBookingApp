package com.project.bookMembership.membership;

import com.project.bookMembership.classes.ClassRequest;
import com.project.bookMembership.classes.TrainingClass;

public interface MembershipService {
    Membership save(MembershipRequest request);
}
