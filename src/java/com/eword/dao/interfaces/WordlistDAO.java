package com.eword.dao.interfaces;

import com.eword.beans.Wordlist;
import java.util.ArrayList;

public interface WordlistDAO {

    Wordlist find(int wordlistId);

    ArrayList<Wordlist> findAll();
}
