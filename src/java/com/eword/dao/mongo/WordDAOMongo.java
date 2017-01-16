package com.eword.dao.mongo;

import com.eword.beans.Word;
import com.eword.dao.interfaces.WordDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import org.bson.Document;

public class WordDAOMongo implements WordDAO {

    private final DAOFactoryMongo daoFactory;

    WordDAOMongo(DAOFactoryMongo daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public ArrayList<Word> findAll(int wordlistId) {
        MongoCollection wordlistCollection = daoFactory.getConnection().getCollection("wordlist");
        Document wordsDocument = (Document) wordlistCollection.find(Filters.eq("wordlist_id", wordlistId)).first();

        ArrayList<Word> words = new ArrayList<>();

        if (wordsDocument != null) {
            for (Document wordDocument : (ArrayList<Document>) wordsDocument.get("words")) {
                Word word = new Word();
                word.setId(wordDocument.getInteger("word_id"));
                word.setWord(wordDocument.getString("word"));
                word.setTranslation(wordDocument.getString("translation"));
                word.setPartOfSpeech(wordDocument.getString("partOfSpeech"));
                word.setExamples(wordDocument.getString("example"));
                words.add(word);
            }
        }

        return words;
    }

}
