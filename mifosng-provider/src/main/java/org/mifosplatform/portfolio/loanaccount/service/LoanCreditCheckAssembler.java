/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.portfolio.loanaccount.service;

import java.util.HashSet;
import java.util.Set;

import org.mifosplatform.infrastructure.core.serialization.FromJsonHelper;
import org.mifosplatform.portfolio.creditcheck.CreditCheckConstants;
import org.mifosplatform.portfolio.creditcheck.domain.CreditCheck;
import org.mifosplatform.portfolio.creditcheck.domain.CreditCheckRepositoryWrapper;
import org.mifosplatform.portfolio.loanaccount.api.LoanApiConstants;
import org.mifosplatform.portfolio.loanaccount.domain.LoanCreditCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Service
public class LoanCreditCheckAssembler {
    private final FromJsonHelper fromJsonHelper;
    private final CreditCheckRepositoryWrapper creditCheckRepositoryWrapper;

    @Autowired
    public LoanCreditCheckAssembler(final FromJsonHelper fromJsonHelper, 
            final CreditCheckRepositoryWrapper creditCheckRepositoryWrapper) {
        this.fromJsonHelper = fromJsonHelper;
        this.creditCheckRepositoryWrapper = creditCheckRepositoryWrapper;
    }
    
    public Set<LoanCreditCheck> toLoanCreditCheck(final JsonElement jsonElement) {
        final Set<LoanCreditCheck> loanCreditChecks = new HashSet<>();
        
        if (jsonElement.isJsonObject()) {
            final JsonObject jsonObject = jsonElement.getAsJsonObject();
            
            if (jsonObject.has(CreditCheckConstants.CREDIT_CHECKS_PARAM_NAME) && 
                    jsonObject.get(CreditCheckConstants.CREDIT_CHECKS_PARAM_NAME).isJsonArray()) {
                final JsonArray jsonArray = jsonObject.get(CreditCheckConstants.CREDIT_CHECKS_PARAM_NAME).getAsJsonArray();
                
                for (int i = 0; i < jsonArray.size(); i++) {
                    final JsonObject loanCreditCheckObject = jsonArray.get(i).getAsJsonObject();
                    final Long creditCheckId = this.fromJsonHelper.extractLongNamed(LoanApiConstants.ID_PARAM_NAME, loanCreditCheckObject);
                    final CreditCheck creditCheck = this.creditCheckRepositoryWrapper.findOneThrowExceptionIfNotFound(creditCheckId);
                    final LoanCreditCheck loanCreditCheck = LoanCreditCheck.instance(creditCheck);
                    
                    loanCreditChecks.add(loanCreditCheck);
                }
            }
        }
        
        return loanCreditChecks;
    }
}
