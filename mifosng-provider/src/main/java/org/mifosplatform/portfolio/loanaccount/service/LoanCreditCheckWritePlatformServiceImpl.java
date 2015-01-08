/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.portfolio.loanaccount.service;

import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.portfolio.loanaccount.domain.LoanCreditCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanCreditCheckWritePlatformServiceImpl implements LoanCreditCheckWritePlatformService {
    private final LoanCreditCheckRepository loanCreditCheckRepository;
    
    @Autowired
    public LoanCreditCheckWritePlatformServiceImpl(final LoanCreditCheckRepository loanCreditCheckRepository) {
        this.loanCreditCheckRepository = loanCreditCheckRepository;
    }

    @Override
    public CommandProcessingResult deactivateLoanCreditCheck(JsonCommand jsonCommand) {
        // TODO Auto-generated method stub
        return null;
    }
}
