package com.eword.dao;

import com.eword.dao.interfaces.*;
import com.eword.beans.Quote;

public interface QuoteDAO {

    /**
     * Returns a random Quote
     *
     * @return a Quote object selected randomly
     */
    Quote findRandom();
}
