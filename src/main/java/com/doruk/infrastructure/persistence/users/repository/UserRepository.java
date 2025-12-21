package com.doruk.infrastructure.persistence.users.repository;

import com.doruk.application.dto.UserDto;
import com.doruk.infrastructure.config.AppExecutors;
import com.doruk.infrastructure.persistence.entity.UserTable;
import lombok.RequiredArgsConstructor;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.ast.Predicate;
import reactor.core.publisher.Mono;

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
