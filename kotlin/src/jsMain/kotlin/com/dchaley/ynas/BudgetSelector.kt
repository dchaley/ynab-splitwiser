package com.dchaley.ynas

import io.kvision.core.Container
import io.kvision.html.button
import io.kvision.html.div
import io.kvision.panel.hPanel
import io.kvision.state.bind
import io.kvision.state.bindEach
import kotlinx.coroutines.flow.StateFlow
import ynab.BudgetSummary

fun Container.budgetSelector(budgetSummaries: StateFlow<List<BudgetSummary>>, onBudgetSelect: (BudgetSummary) -> Unit) {

  div().bind(budgetSummaries) { budgets ->
    if (budgets.isEmpty()) {
      hPanel {
        Spinner()
        div("Loading budgets…")
      }
    }
    else {
      hPanel(spacing = 5) {
      }.bindEach(budgetSummaries) { budget ->
        button(text = budget.name) {
          onClick {
            // Call the on-select callback
            onBudgetSelect(budget)
          }
        }
      }
    }
  }
}
