package com.cn.m.dsl.twitter

import twitter4j.Twitter

/**
 * Created by macx on 7/2/14.
 */

def twitterId = 'nothingismao@gmail.com'
def password = '870908mao'
def twitter = new Twitter(twitterId,password)
twitter.updateStatus("Updating my status via the Twotter4j APIS")
println(twitter.getUserLists(twitterId))


