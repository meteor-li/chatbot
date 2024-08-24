package cn.lxing.zsxq;

import cn.lxing.zsxq.mode.aggregates.UnAnsweredQuestionsAggregates;

import java.io.IOException;

/**
 *  知识星球 API 接口
 */
public interface IZsxqApi {
    UnAnsweredQuestionsAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException;

    boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException;


}
