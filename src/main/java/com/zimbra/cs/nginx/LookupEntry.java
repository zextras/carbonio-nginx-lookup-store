// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.nginx;

public abstract class LookupEntry {
    private String mKey;
    
    LookupEntry(String key) {
        mKey = key;
    }
    
    String getKey() {
        return mKey;
    }
}
