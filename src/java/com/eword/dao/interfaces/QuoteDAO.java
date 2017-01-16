package com.eword.dao.interfaces;

import com.eword.beans.Quote;

public interface QuoteDAO {

    /**
     * Returns a random Quote object
     *
     * @return a Quote object selected randomly
     */
    Quote findRandom();
}
