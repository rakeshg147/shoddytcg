/**
 *  Copyright (C) 2009 ShoddyTCG Developer Team
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *  
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.shoddytcg.server.network.codec;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineEncoder;

/**
 * Encodes messages to be sent to the client
 * @author shadowkanji
 *
 */
public class PokenetEncoder extends ProtocolEncoderAdapter {
	private static final String ENCODER = TextLineEncoder.class.getName() + ".encoder";
	private final Charset m_charset;
	private final LineDelimiter m_delimiter;
	
	/**
	 * Default constructor
	 */
	public PokenetEncoder() {
		m_charset = Charset.forName("US-ASCII");
		m_delimiter = LineDelimiter.UNIX;
	}

	public void encode(IoSession session, Object message, ProtocolEncoderOutput out)
			throws Exception {
		CharsetEncoder encoder = (CharsetEncoder) session.getAttribute(ENCODER);
        if (encoder == null) {
            encoder = m_charset.newEncoder();
            session.setAttribute(ENCODER, encoder);
        }

        String value = message.toString();
        IoBuffer buf = IoBuffer.allocate(value.length())
                .setAutoExpand(true);
        buf.putString(value, encoder);
        if (buf.position() > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Line length: " + buf.position());
        }
        buf.putString(m_delimiter.getValue(), encoder);
        buf.flip();
        out.write(buf);
	}

}
