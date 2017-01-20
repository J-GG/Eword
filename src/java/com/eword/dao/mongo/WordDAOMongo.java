package com.eword.dao.mongo;

import com.eword.beans.Word;
import com.eword.dao.interfaces.WordDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import org.bson.Document;

public class WordDAOMongo implements WordDAO, RequestAttribute {

    /**
     * Interface to access the MongoDB database
     */
    private final MongoDatabase MONGO_DATABASE;

    /**
     * Create an interface to access data regarding the words
     *
     * @param database The database where data is stored
     */
    WordDAOMongo(MongoDatabase database) {
        this.MONGO_DATABASE = database;
    }

    @Override
    public ArrayList<Word> findAll(int wordlistId) {

        ArrayList<Word> words = new ArrayList<>();
        MongoCollection wordlistCollection = MONGO_DATABASE.getCollection(COLLECTION_WORDLIST);

        //We search for the wordlist with the given id
        Document wordsDocument = (Document) wordlistCollection.find(Filters.eq(COLLECTION_WORDLIST_ID, wordlistId)).first();

        //If the document exists a list of Word object is created and populated with their information
        if (wordsDocument != null) {
            //The words of a wordlist are contained in the field "words"
            for (Document wordDocument : (ArrayList<Document>) wordsDocument.get(COLLECTION_WORDLIST_WORDS)) {
                Word word = new Word();
                word.setId(wordDocument.getInteger(COLLECTION_WORD_ID));
                word.setWord(wordDocument.getString(COLLECTION_WORD_WORD));
                word.setTranslation(wordDocument.getString(COLLECTION_WORD_TRANSLATION));
                word.setPartOfSpeech(wordDocument.getString(COLLECTION_WORD_PART_OF_SPEECH));
                word.setExamples(wordDocument.getString(COLLECTION_WORD_EXAMPLE));
                words.add(word);
            }
        }

        return words;
    }

}
