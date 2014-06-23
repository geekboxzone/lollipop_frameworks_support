/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.support.v4.media.session;

import android.media.MediaMetadata;
import android.media.Rating;
import android.media.session.MediaController;
import android.media.session.MediaSessionToken;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.view.KeyEvent;

class MediaControllerCompatApi21 {
    public static Object fromToken(Object sessionToken) {
        return MediaController.fromToken((MediaSessionToken)sessionToken);
    }

    public static Object createCallback(Callback callback) {
        return new CallbackProxy<Callback>(callback);
    }

    public static void addCallback(Object controllerObj, Object callbackObj, Handler handler) {
        ((MediaController)controllerObj).addCallback(
                (MediaController.Callback)callbackObj, handler);
    }

    public static void removeCallback(Object controllerObj, Object callbackObj) {
        ((MediaController)controllerObj).removeCallback((MediaController.Callback)callbackObj);
    }

    public static Object getTransportControls(Object controllerObj) {
        return ((MediaController)controllerObj).getTransportControls();
    }

    public static Object getPlaybackState(Object controllerObj) {
        return ((MediaController)controllerObj).getPlaybackState();
    }

    public static Object getMetadata(Object controllerObj) {
        return ((MediaController)controllerObj).getMetadata();
    }

    public static int getRatingType(Object controllerObj) {
        return ((MediaController)controllerObj).getRatingType();
    }

    public static Object getVolumeInfo(Object controllerObj) {
        return ((MediaController)controllerObj).getVolumeInfo();
    }

    public static boolean dispatchMediaButtonEvent(Object controllerObj, KeyEvent event) {
        return ((MediaController)controllerObj).dispatchMediaButtonEvent(event);
    }

    public static void sendControlCommand(Object controllerObj,
            String command, Bundle params, ResultReceiver cb) {
        ((MediaController)controllerObj).sendControlCommand(command, params, cb);
    }

    public static class TransportControls {
        public static void play(Object controlsObj) {
            ((MediaController.TransportControls)controlsObj).play();
        }

        public static void pause(Object controlsObj) {
            ((MediaController.TransportControls)controlsObj).pause();
        }

        public static void stop(Object controlsObj) {
            ((MediaController.TransportControls)controlsObj).stop();
        }

        public static void seekTo(Object controlsObj, long pos) {
            ((MediaController.TransportControls)controlsObj).seekTo(pos);
        }

        public static void fastForward(Object controlsObj) {
            ((MediaController.TransportControls)controlsObj).fastForward();
        }

        public static void rewind(Object controlsObj) {
            ((MediaController.TransportControls)controlsObj).rewind();
        }

        public static void skipToNext(Object controlsObj) {
            ((MediaController.TransportControls)controlsObj).skipToNext();
        }

        public static void skipToPrevious(Object controlsObj) {
            ((MediaController.TransportControls)controlsObj).skipToPrevious();
        }

        public static void setRating(Object controlsObj, Object ratingObj) {
            ((MediaController.TransportControls)controlsObj).setRating((Rating)ratingObj);
        }
    }

    public static class VolumeInfo {
        public static int getVolumeType(Object volumeInfoObj) {
            return ((MediaController.VolumeInfo)volumeInfoObj).getVolumeType();
        }

        public static int getAudioStream(Object volumeInfoObj) {
            return ((MediaController.VolumeInfo)volumeInfoObj).getAudioStream();
        }

        public static int getVolumeControl(Object volumeInfoObj) {
            return ((MediaController.VolumeInfo)volumeInfoObj).getVolumeControl();
        }

        public static int getMaxVolume(Object volumeInfoObj) {
            return ((MediaController.VolumeInfo)volumeInfoObj).getMaxVolume();
        }

        public static int getCurrentVolume(Object volumeInfoObj) {
            return ((MediaController.VolumeInfo)volumeInfoObj).getCurrentVolume();
        }
    }

    public static interface Callback {
        public void onSessionEvent(String event, Bundle extras);
        public void onPlaybackStateChanged(Object stateObj);
        public void onMetadataChanged(Object metadataObj);
    }

    static class CallbackProxy<T extends Callback> extends MediaController.Callback {
        protected final T mCallback;

        public CallbackProxy(T callback) {
            mCallback = callback;
        }

        @Override
        public void onSessionEvent(String event, Bundle extras) {
            mCallback.onSessionEvent(event, extras);
        }

        @Override
        public void onPlaybackStateChanged(PlaybackState state) {
            mCallback.onPlaybackStateChanged(state);
        }

        @Override
        public void onMetadataChanged(MediaMetadata metadata) {
            mCallback.onMetadataChanged(metadata);
        }
    }
}
