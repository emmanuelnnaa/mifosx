/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.portfolio.loanaccount.loanschedule.domain;

import java.math.BigDecimal;

import org.joda.time.LocalDate;
import org.mifosplatform.organisation.monetary.domain.Money;
import org.mifosplatform.portfolio.loanaccount.loanschedule.data.LoanSchedulePeriodData;

/**
 * Domain representation of a Loan Schedule Disbursement Period (not used for
 * persistence)
 */
public final class LoanScheduleModelDisbursementPeriod implements LoanScheduleModelPeriod {

    @SuppressWarnings("unused")
    private final Integer periodNumber;
    private final LocalDate disbursementDate;
    private final Money principalDisbursed;
    private final BigDecimal chargesDueAtTimeOfDisbursement;
    private final BigDecimal chargesRepaidAtDisbursement;

    public static LoanScheduleModelDisbursementPeriod disbursement(final LoanApplicationTerms loanApplicationTerms,
            final BigDecimal chargesDueAtTimeOfDisbursement, final BigDecimal chargesRepaidAtDisbursement) {

        final int periodNumber = 0;
        return new LoanScheduleModelDisbursementPeriod(periodNumber, loanApplicationTerms.getExpectedDisbursementDate(),
                loanApplicationTerms.getPrincipal(), chargesDueAtTimeOfDisbursement, chargesRepaidAtDisbursement);
    }

    public static LoanScheduleModelDisbursementPeriod disbursement(final LocalDate disbursementDate, final Money principalDisbursed,
            final BigDecimal chargesDueAtTimeOfDisbursement, final BigDecimal chargesRepaidAtDisbursement) {
        return new LoanScheduleModelDisbursementPeriod(null, disbursementDate, principalDisbursed, chargesDueAtTimeOfDisbursement, chargesRepaidAtDisbursement);
    }

    private LoanScheduleModelDisbursementPeriod(final Integer periodNumber, final LocalDate disbursementDate,
            final Money principalDisbursed, final BigDecimal chargesDueAtTimeOfDisbursement, 
            final BigDecimal chargesRepaidAtDisbursement) {
        this.periodNumber = periodNumber;
        this.disbursementDate = disbursementDate;
        this.principalDisbursed = principalDisbursed;
        this.chargesDueAtTimeOfDisbursement = chargesDueAtTimeOfDisbursement;
        this.chargesRepaidAtDisbursement = chargesRepaidAtDisbursement;
    }

    @Override
    public LoanSchedulePeriodData toData() {
        return LoanSchedulePeriodData.disbursementOnlyPeriod(this.disbursementDate, this.principalDisbursed.getAmount(),
                this.chargesDueAtTimeOfDisbursement, false, this.chargesRepaidAtDisbursement);
    }

    @Override
    public boolean isRepaymentPeriod() {
        return false;
    }

    @Override
    public Integer periodNumber() {
        return null;
    }

    @Override
    public LocalDate periodFromDate() {
        return null;
    }

    @Override
    public LocalDate periodDueDate() {
        return null;
    }

    @Override
    public BigDecimal principalDue() {
        return null;
    }

    @Override
    public BigDecimal interestDue() {
        return null;
    }

    @Override
    public BigDecimal feeChargesDue() {
        return null;
    }

    @Override
    public BigDecimal penaltyChargesDue() {
        return null;
    }

    @Override
    public void addLoanCharges(@SuppressWarnings("unused") BigDecimal feeCharge, @SuppressWarnings("unused") BigDecimal penaltyCharge) {
        return;
    }

    @Override
    public boolean isRecalculatedInterestComponent() {
        return false;
    }

	public BigDecimal getChargesRepaidAtDisbursement() {
		return chargesRepaidAtDisbursement;
	}
}