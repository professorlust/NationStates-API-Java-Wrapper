package com.github.agadar.nsapi;

import com.github.agadar.nsapi.domain.nation.Nation;
import com.github.agadar.nsapi.domain.region.Region;
import com.github.agadar.nsapi.domain.shared.CensusScoreHistory;
import com.github.agadar.nsapi.domain.wa.DelLogEntry;
import com.github.agadar.nsapi.domain.wa.WorldAssembly;
import com.github.agadar.nsapi.domain.world.World;
import com.github.agadar.nsapi.enums.Council;
import com.github.agadar.nsapi.enums.shard.NationShard;
import com.github.agadar.nsapi.enums.shard.RegionShard;
import com.github.agadar.nsapi.enums.shard.WAShard;
import com.github.agadar.nsapi.enums.shard.WorldShard;
import com.github.agadar.nsapi.query.NSQuery;

/**
 *
 * @author Agadar <https://github.com/Agadar/>
 */
public class TestMain
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NationStatesAPIException, InterruptedException
    {
        NSQuery.misc().verifyVersion().execute();
    }
    
}
