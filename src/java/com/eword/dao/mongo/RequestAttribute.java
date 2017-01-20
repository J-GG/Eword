package com.eword.dao.mongo;

public interface RequestAttribute {

    /**
     * Name of the counter collection
     */
    static final String COLLECTION_COUNTER = "counter";

    /**
     * Name of the field containing the name of the counter
     */
    static final String COLLECTION_COUNTER_NAME = "_id";

    /**
     * Name of the field containing the sequence of the counter
     */
    static final String COLLECTION_COUNTER_SEQUENCE = "seq";

    /**
     * Name of the quote collection
     */
    static final String COLLECTION_QUOTE = "quote";

    /**
     * Name of the field containing the author of the quote
     */
    static final String COLLECTION_QUOTE_AUTHOR = "author";

    /**
     * Name of the field containing the content of the quote
     */
    static final String COLLECTION_QUOTE_CONTENT = "content";

    /**
     * Name of the field containing the quote id
     */
    static final String COLLECTION_QUOTE_ID = "quote_id";

    /**
     * Name of the counter collection
     */
    static final String COLLECTION_USER = "user";

    /**
     * Name of the field containing the user's id
     */
    static final String COLLECTION_USER_ID = "user_id";

    /**
     * Name of the field containing the user's language
     */
    static final String COLLECTION_USER_LANGUAGE = "language";

    /**
     * Name of the field containing the user's password
     */
    static final String COLLECTION_USER_PASSWORD = "password";

    /**
     * Name of the counter collection
     */
    static final String COLLECTION_USER_TOKEN = "token";

    /**
     * Name of the field containing the user's username
     */
    static final String COLLECTION_USER_USERNAME = "username";

    /**
     * Name of the wordlist collection
     */
    static final String COLLECTION_WORDLIST = "wordlist";
    /**
     * Name of the field containing the destination language of the wordlist
     */
    static final String COLLECTION_WORDLIST_DESTINATION = "destination";

    /**
     * Name of the field containing the id of the wordlist
     */
    static final String COLLECTION_WORDLIST_ID = "wordlist_id";

    /**
     * Name of the field containing the source language of the wordlist
     */
    static final String COLLECTION_WORDLIST_SOURCE = "source";

    /**
     * Name of the field containing the title of the wordlist
     */
    static final String COLLECTION_WORDLIST_TITLE = "title";

    /**
     * Name of the field containing the owner of the wordlist
     */
    static final String COLLECTION_WORDLIST_USER = "user";

    /**
     * Name of the field containing the list of words
     */
    static final String COLLECTION_WORDLIST_WORDS = "words";

    /**
     * Name of the field containing the example of the word
     */
    static final String COLLECTION_WORD_EXAMPLE = "example";

    /**
     * Name of the field containing the id of the word
     */
    static final String COLLECTION_WORD_ID = "word_id";

    /**
     * Name of the field containing the part of speech of the word
     */
    static final String COLLECTION_WORD_PART_OF_SPEECH = "partOfSpeech";

    /**
     * Name of the field containing the translation of the word
     */
    static final String COLLECTION_WORD_TRANSLATION = "translation";

    /**
     * Name of the field containing the value of the word
     */
    static final String COLLECTION_WORD_WORD = "word";
}
