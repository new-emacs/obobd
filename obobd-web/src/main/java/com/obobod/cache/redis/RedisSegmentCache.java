package com.obobod.cache.redis;


import mondrian.spi.SegmentBody;
import mondrian.spi.SegmentCache;
import mondrian.spi.SegmentHeader;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;


public class RedisSegmentCache implements SegmentCache {

    private static final Logger log = Logger.getLogger(RedisSegmentCache.class);
    private final List<SegmentCacheListener> listeners =
            new CopyOnWriteArrayList<SegmentCacheListener>();

    public SegmentBody get(SegmentHeader header) {
        RedisDao map = getRedisDao();

        if (header == null)
            return null;

        final byte[] ref = map.get(Md5Utils.md5sum(header.toString()));
        final SegmentBody body = (SegmentBody) SerializeUtil.unserialize(ref);
        if (body == null) {
            return null;
        } else {
            Map valuemap = body.getValueMap();
            if (valuemap == null || valuemap.size() == 0) {
                this.remove(header);
                log.info("RedisSegmentCache execute valuemap==null||valuemap.size()==0,this.remove(header)!");
            } else {
                log.info("RedisSegmentCache execute get sucess!");
            }

        }

        return body;
    }

    public boolean contains(SegmentHeader header) {
        if (header == null)
            return false;
        RedisDao map = getRedisDao();
        final byte[] ref = map.get(Md5Utils.md5sum(header.toString()));
        if (ref == null) {
            return false;
        }
        final SegmentBody body = (SegmentBody) SerializeUtil.unserialize(ref);
        if (body == null) {
            try {
                this.remove(header);
            } catch (Exception e) {
                log.error("SegmentBody contains error：" + e.getMessage());
            }
            return false;
        }
        log.info("RedisSegmentCache execute contains sucess!");
        return true;
    }

    private RedisDao getRedisDao() {
        return new RedisDao();
    }

    public List<SegmentHeader> getSegmentHeaders() {
        return null;
    }

    public boolean put(final SegmentHeader header, SegmentBody body) {
        assert header != null;
        assert body != null;

        RedisDao map = getRedisDao();
        try {
            map.put(Md5Utils.md5sum(header.toString()), SerializeUtil.serialize(body));
        } catch (Exception e) {
            log.error("SegmentBody put error：" + e.getMessage());
        }
        fireSegmentCacheEvent(
                new SegmentCache.SegmentCacheListener.SegmentCacheEvent() {
                    public boolean isLocal() {
                        return false;
                    }

                    public SegmentHeader getSource() {
                        return header;
                    }

                    public EventType getEventType() {
                        return SegmentCacheListener.SegmentCacheEvent
                                .EventType.ENTRY_CREATED;
                    }
                });
        log.info("RedisSegmentCache execute put sucess!");
        return true; // success
    }

    public boolean remove(final SegmentHeader header) {
        if (header == null)
            return false;
        RedisDao map = getRedisDao();
        boolean result = false;
        try {
            result = map.remove(Md5Utils.md5sum(header.toString())) != null;

//	        if (result) {
            fireSegmentCacheEvent(
                    new SegmentCache.SegmentCacheListener.SegmentCacheEvent() {
                        public boolean isLocal() {
                            return true;
                        }

                        public SegmentHeader getSource() {
                            return header;
                        }

                        public EventType getEventType() {
                            return
                                    SegmentCacheListener.SegmentCacheEvent
                                            .EventType.ENTRY_DELETED;
                        }
                    });
//	        }
        } catch (Exception e) {
            log.error("SegmentBody remove error：" + e.getMessage());
        }
        log.info("RedisSegmentCache execute remove sucess!");
        return result;
    }

    public void tearDown() {
        RedisDao map = getRedisDao();
        map.clear();
        listeners.clear();
        log.info("RedisSegmentCache execute tearDown sucess!");
    }

    public void addListener(SegmentCacheListener listener) {
        listeners.add(listener);
    }

    public void removeListener(SegmentCacheListener listener) {
        listeners.remove(listener);
    }

    public boolean supportsRichIndex() {
        return true;
    }

    public void fireSegmentCacheEvent(
            SegmentCache.SegmentCacheListener.SegmentCacheEvent evt) {
        for (SegmentCacheListener listener : listeners) {
            listener.handle(evt);
        }
    }
}
