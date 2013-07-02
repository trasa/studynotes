using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ConnectFour.Game
{
    /// <summary>
    /// Examine the state of a Container after a piece has been added.
    /// Is this Container now in a "win" state?
    /// </summary>
    public class WinDetector
    {
        private readonly Container container;
        private readonly PieceColor color;
        private readonly uint column;
        private readonly uint row;

        public WinDetector(Container container, PieceColor color, uint column, uint row)
        {
            this.container = container;
            this.color = color;
            this.column = column;
            this.row = row;
        }

        public bool IsWinner
        {
            get { return WinsHorizontally || WinsVertically || WinsPositiveDiagonally || WinsNegativeDiagonally; }
        }

        protected bool WinsNegativeDiagonally
        {
            get
            {
                return container.GetPieceCountNegativeUp(color, (int) column, (int) row) +
                       container.GetPieceCountNegativeDown(color, (int) column, (int) row) + 1 >= 4;
            }
        }

        protected bool WinsPositiveDiagonally
        {
            get
            {
                return container.GetPieceCountPositiveUp(color, (int) column, (int) row) +
                       container.GetPieceCountPositiveDown(color, (int) column, (int) row) + 1 >= 4;
            }
        }

        protected bool WinsVertically
        {
            get
            {
                return container.GetPieceCountUp(color, column, (int) row) +
                       container.GetPieceCountDown(color, column, (int) row) + 1 >= 4;
            }
        }

        protected bool WinsHorizontally
        {
            get
            {
                return container.GetPieceCountLeft(color, (int) column, row) +
                       container.GetPieceCountRight(color, (int) column, row) + 1 >= 4;
            }
        }
    }
}
