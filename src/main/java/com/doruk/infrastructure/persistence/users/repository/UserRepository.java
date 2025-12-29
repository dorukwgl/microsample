package com.doruk.infrastructure.persistence.users.repository;

import com.doruk.infrastructure.config.AppExecutors;
import lombok.RequiredArgsConstructor;
import org.babyfish.jimmer.sql.JSqlClient;

@RequiredArgsConstructor
public class UserRepository {
    private final JSqlClient slqClient;
    private final AppExecutors executors;

//    public Mono<UserDto> findByEmailOrUsername(String field) {
//        var table = UserTable.$;
//        return Mono.fromCallable(() -> slqClient.createQuery(table)
//                    .where(Predicate.or(
//                            table.username().eq(field),
//                            table.email().eq(field)
//                    ))
//                    .select(table)
//                    .fetchFirst())
//                .subscribeOn(executors.BLOCKING);
//    }


}
