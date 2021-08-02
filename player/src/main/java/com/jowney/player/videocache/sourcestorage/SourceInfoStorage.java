package com.jowney.player.videocache.sourcestorage;

import com.jowney.player.videocache.SourceInfo;

/**
 * Storage for {@link SourceInfo}.
 *
 * @author Alexey Danilov (danikula@gmail.com).
 */
public interface SourceInfoStorage {

    SourceInfo get(String url);

    void put(String url, SourceInfo sourceInfo);

    void release();
}
