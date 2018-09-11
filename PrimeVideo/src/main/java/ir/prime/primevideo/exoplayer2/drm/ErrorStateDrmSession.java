/*
 * Copyright (C) 2017 The Android Open Source Project
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
package ir.prime.primevideo.exoplayer2.drm;

import java.util.Map;

import ir.prime.primevideo.exoplayer2.util.Assertions;

/**
 * A {@link DrmSession} that's in a terminal error state.
 */
/* package */ final class ErrorStateDrmSession<T extends ExoMediaCrypto> implements DrmSession<T> {

  private final DrmSessionException error;

  public ErrorStateDrmSession(DrmSessionException error) {
    this.error = Assertions.checkNotNull(error);
  }

  @Override
  public int getState() {
    return STATE_ERROR;
  }

  @Override
  public DrmSessionException getError() {
    return error;
  }

  @Override
  public T getMediaCrypto() {
    return null;
  }

  @Override
  public Map<String, String> queryKeyStatus() {
    return null;
  }

  @Override
  public byte[] getOfflineLicenseKeySetId() {
    return null;
  }

}