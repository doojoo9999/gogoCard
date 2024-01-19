package com.teamsparta.gogocard.domain.gogocard.repository

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Expression
import com.querydsl.core.types.Order
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.EntityPathBase
import com.querydsl.core.types.dsl.PathBuilder
import com.teamsparta.gogocard.domain.gogocard.model.CardEntity
import com.teamsparta.gogocard.domain.gogocard.model.Complete
import com.teamsparta.gogocard.domain.gogocard.model.QCardEntity
import com.teamsparta.gogocard.domain.gogocard.model.QCommentEntity
import com.teamsparta.gogocard.infra.querydsl.QueryDslSupport
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class CardRepositoryImpl : QueryDslSupport(), CustomCardRepository {

    private val card = QCardEntity.cardEntity

    override fun searchCardListByTitle(title: String) : List<CardEntity>{
        return queryFactory.selectFrom(card)
            .where(card.title.containsIgnoreCase(title))
            .fetch()
    }

    override fun searchCardListByComplete(isCompleted: Boolean) : List<CardEntity>{
        return queryFactory.selectFrom(card)
            .where(card._isCompleted.eq(isCompleted))
            .fetch()
    }

    override fun findByPageableAndComplete(pageable: Pageable, cardComplete: Complete?): Page<CardEntity> {

        val whereClause = BooleanBuilder()
        cardComplete?.let { whereClause.and(card._isCompleted.eq(it == Complete.NO_COMPLETE)) }


        val totalCount = queryFactory.select(card.count()).from(card).where(whereClause).fetchOne() ?: 0L

//        val query = queryFactory.selectFrom(card)
//            .where(whereClause)
//            .offset(pageable.offset)
//            .limit(pageable.pageSize.toLong())
//            .orderBy(card._isCompleted.asc())
//
//        if (pageable.sort.isSorted){
//            when (pageable.sort.first()?.property ){
//                "id" -> query.orderBy(card.id.asc())
//                "title" -> query.orderBy(card.title.asc())
//                else ->  query.orderBy(card.id.asc())
//            }
//
//        } else {
//            query.orderBy(card.id.asc())
//        }

        val comment = QCommentEntity.commentEntity
        val contents = queryFactory.selectFrom(card)
            .where(whereClause)
            .leftJoin(card.comments, comment)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .orderBy(*getOrderSpecifier(pageable, card))
            .fetch()

        return PageImpl(contents, pageable, totalCount)
    }

    private fun getOrderSpecifier(pageable: Pageable, path: EntityPathBase<*>): Array<OrderSpecifier<*>> {
        val pathBuilder = PathBuilder(path.type, path.metadata)

        return pageable.sort.toList().map {
            order -> OrderSpecifier(
                if (order.isAscending) Order.ASC else Order.DESC,
                pathBuilder.get(order.property) as Expression<Comparable<*>>
            )
        }.toTypedArray()
    }

}