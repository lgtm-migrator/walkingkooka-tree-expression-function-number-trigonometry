/*
 * Copyright 2020 Miroslav Pokorny (github.com/mP1)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package walkingkooka.tree.expression.function.number.trigonometry;

import ch.obermuhlner.math.big.BigDecimalMath;
import walkingkooka.Cast;
import walkingkooka.NeverError;
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.ExpressionNumberKind;
import walkingkooka.tree.expression.FunctionExpressionName;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;

import java.util.List;

final class NumberExpressionFunctionPi<C extends ExpressionFunctionContext> extends NumberExpressionFunction<C>{

    static <C extends ExpressionFunctionContext> NumberExpressionFunctionPi<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static NumberExpressionFunctionPi<?> INSTANCE = new NumberExpressionFunctionPi<>();

    private NumberExpressionFunctionPi() {
        super();
    }

    @Override
    public FunctionExpressionName name() {
        return NAME;
    }

    private final static FunctionExpressionName NAME = FunctionExpressionName.with("pi");

    @Override
    public ExpressionNumber apply(final List<Object> parameters,
                                  final C context) {
        this.checkOnlyRequiredParameters(parameters);

        ExpressionNumber pi = null;

        final ExpressionNumberKind kind = context.expressionNumberKind();

        switch(kind) {
            case BIG_DECIMAL:
                pi = ExpressionNumberKind.BIG_DECIMAL.create(
                        BigDecimalMath.pi(context.mathContext())
                );
                break;
            case DOUBLE:
                pi = DOUBLE;
                break;
            default:
                NeverError.unhandledCase(pi, ExpressionNumberKind.values());
                break;
        }

        return pi;
    }

    private final static ExpressionNumber DOUBLE = ExpressionNumberKind.DOUBLE.create(Math.PI);

    @Override
    public List<ExpressionFunctionParameter<?>> parameters() {
        return ExpressionFunctionParameter.EMPTY;
    }
}